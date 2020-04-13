package com.hulunbuir.clam.admin.study;

/**
 * <p>
 * Explain:
 * </p >
 *
 * @author wangjunming
 * @since 2019-08-26 21:56
 */
public class ZookeeperStudy {


    /**
     * zookeeper的选举机制：
     *      1、每次服务器都是选自己为master服务器。
     *      2、然后在选择ID号大的服务器，若是最终选定了master服务器，则后面的服务器符合条件，那也不去选择，一旦确定则不能改变
     *
     * zookeeper的类型：
     * 持久性：当客户端与服务端断开连接之后，创建的节点，在服务端中的数据仍然保存
     *
     * 短暂性：当客户端与服务端断开连接之后，创建的节点，自己删除在服务端中的数据
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * @param args
     */
    public static void main(String[] args) {

    }

}
