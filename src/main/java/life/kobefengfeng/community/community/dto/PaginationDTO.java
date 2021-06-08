package life.kobefengfeng.community.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author KobeFL
 * @Description TODO 该对象包裹页面所承载的一些元素:包括首页，前一页
 * @Date 2021/6/7 21:05
 * @Version 1.0
 */
//正常是前端做这件事，但是本课程是弱化这件事情
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;//默认false 是否有向前按钮
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;//当前所在页
    private List<Integer> pages = new ArrayList<>();//当前显示页,所包含的页码   创建新对象，便于抛异常
    private Integer totalPage;//展示所有的页数。显示的页数,每页5个，如果12行，则每页显示5个时，显示3页


    public void setPagination(Integer totalCount, Integer page, Integer size) {
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else{
            totalPage = totalCount / size + 1;
        }
        //容错处理
        if(page<1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        this.page = page;//把当前页赋值下来
        pages.add(page);
        for(int i = 1;i <= 3;i++){
            if(page - i > 0){//当
                pages.add(0,page - i);
            }
            if(page + i <= totalPage){
                pages.add(page + i);
            }
        }
        //是否展示上一页
        if(page == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        //是否展示下一页
        if(page == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }

        //是否展示第一页
        if(pages.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }

        //是否展示最后一页
        if(pages.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }

    }
}