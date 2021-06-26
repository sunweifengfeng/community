package life.kobefengfeng.community.community.service;

import life.kobefengfeng.community.community.dto.NotificationDTO;
import life.kobefengfeng.community.community.dto.PaginationDTO;
import life.kobefengfeng.community.community.enums.NotificationStatusEnum;
import life.kobefengfeng.community.community.enums.NotificationTypeEnum;
import life.kobefengfeng.community.community.exception.CustomizeErrorCode;
import life.kobefengfeng.community.community.exception.CustomizeException;
import life.kobefengfeng.community.community.mapper.NotificationMapper;
import life.kobefengfeng.community.community.model.Notification;
import life.kobefengfeng.community.community.model.NotificationExample;
import life.kobefengfeng.community.community.model.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/6/26 16:04
 * @Version 1.0
 */
@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;

    //根据用户的id查询 与其相关的回复
    public PaginationDTO list(Long id, Integer page, Integer size) {
        //查询数据库之前判断页面是否符合要求
        PaginationDTO<NotificationDTO> paginationDTO = new PaginationDTO<>();

        //根据用户的id,在notification表中查询creator等于id的 所有通知的数量
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id);
        Integer totalCount = (int) notificationMapper.countByExample(notificationExample);

        Integer totalPage;
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }

        if(page<1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage,page);//创建一个方法，根据三个参数计算页面展示所需要的元素

        //size*(page-1)
        Integer offset = size*(page-1);
        //查到所有Notification对象
        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        //分页查询 每一页中显示通知
        List<Notification> notifications = notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));

        //如果没有通知 则返回空
        if(notifications.size() == 0){
            return paginationDTO;
        }

        //将notifications转换为notificationDTOs 因为还要显示用户的一些信息 在DTO中定义的
        List<NotificationDTO> notificationDTOList = new ArrayList<>();
        for (Notification notification : notifications) {
            NotificationDTO notificationDTO = new NotificationDTO();
            BeanUtils.copyProperties(notification,notificationDTO);
            notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));//显示在页面上的那块
            notificationDTOList.add(notificationDTO);
        }
        paginationDTO.setData(notificationDTOList);
        return paginationDTO;
    }

    //查询未读的通知的数量
    public Long unreadCount(Long id) {
        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(id)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStatus());//加了一个where条件 状态是未读的
        //notification数据库中有多少个Receiver等于userId 就有多少个未读通知的数量
        long unreadCount = notificationMapper.countByExample(notificationExample);
        return unreadCount;
    }

    //读取notification 并将其status标为已读
    public NotificationDTO read(Long id, User user) {
        //根据前端传过来的id 去notification数据库中查找相应的行
        Notification notification = notificationMapper.selectByPrimaryKey(id);

        //通知删除 或不见了
        if(notification == null){
            throw new CustomizeException(CustomizeErrorCode.NOTIFICATION_NOT_FOUND);
        }
        //如果根据notification的id查到的接收者不是 我登录的用户的id则抛异常
        //黑客 非法入侵 或者网址中输入了其他接受者的id
        if(!notification.getReceiver().equals(user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        //将评论标为已读
        notification.setStatus(NotificationStatusEnum.READ.getStatus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDTO notificationDTO = new NotificationDTO();
        BeanUtils.copyProperties(notification,notificationDTO);
        notificationDTO.setTypeName(NotificationTypeEnum.nameOfType(notification.getType()));//显示在页面上的那块
        return notificationDTO;
    }
}
