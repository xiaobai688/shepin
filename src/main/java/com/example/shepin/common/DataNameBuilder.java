package com.example.shepin.common;

/**
 * @author opec
 */
public interface DataNameBuilder {
    /**
     * 构建数据节点名称
     * @param method 方法名
     * @return 返回数据节点名称
     */
    String build(String method);
}
