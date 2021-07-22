package life.kobefengfeng.community.community.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @Author KobeFL
 * @Description TODO
 * @Date 2021/7/22 21:37
 * @Version 1.0
 */
@Data
public class HotTagDTO implements Comparable {
    private String name;
    private Integer priority;

    //由于HotTagDTO要实现Topn，在QueuePriority中添加，因此她要实现Comparable接口 并且重写CompareTo方法
    //同时compareTo方法小顶堆实现，在源码中有当前值大于原来父类的值时 就不插入了，因此this指针的getPriority在前面
    @Override
    public int compareTo(@NotNull Object o) {
        return this.getPriority() - ((HotTagDTO) o).getPriority();
    }
}
