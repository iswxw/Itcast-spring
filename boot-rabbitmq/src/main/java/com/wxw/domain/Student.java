package com.wxw.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    /**
     * 学生编号
     *
     * @mbggenerated
     */
    private Integer id;

    /**
     * 学生姓名
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 所在班级
     *
     * @mbggenerated
     */
    private String classes;

    /**
     * 分数
     *
     * @mbggenerated
     */
    private Integer score;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Date time;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", classes=").append(classes);
        sb.append(", score=").append(score);
        sb.append(", time=").append(time);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}