package life.kobefengfeng.community.community.cache;

import life.kobefengfeng.community.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/21 19:51
 * @Version 1.0
 */
@Component
@Data
public class HotTagCache {
    private Map<String,Integer> tags = new HashMap<>();
    //存放topn
    private List<String> hots = new ArrayList<>();

    public void updateTags(Map<String,Integer> tags){
        //定义三个的作用是只显示热度前三的问题
        int max = 3;
        //初始化优先队列的指定长度
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max);
        //遍历map
        tags.forEach((name,priority)->{
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setName(name);
            hotTagDTO.setPriority(priority);
            //小顶堆中个数小于3 则将map放进去
            if(priorityQueue.size() < 3){
                priorityQueue.add(hotTagDTO);//add进去的元素 对象要实现compareble接口 要去HotTag中实现此接口
            }else{//小顶堆中个数小于3 则将map放进去
                //拿到堆顶 最小的元素
                HotTagDTO minHot = priorityQueue.peek();
                //当前需要插入的元素的优先级大于队列中的最小优先级 就要将原来的删除 插入新的
                if(hotTagDTO.compareTo(minHot) > 0){
                    //将最小的元素拿出来
                    priorityQueue.poll();
                    //插入我新的元素 内部将重排小顶堆
                    priorityQueue.add(hotTagDTO);
                }
            }
        });

        List<String> sortedTags = new ArrayList<>();
        //得到topn
        //获得并删除 堆顶元素
        HotTagDTO poll = priorityQueue.poll();
        while(poll != null){
            sortedTags.add(0,poll.getName());
            poll = priorityQueue.poll();
        }
        hots = sortedTags;
    }
}
