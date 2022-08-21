package geektime.spring.springbucks.jpademo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.joda.money.Money;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "T_MENU")
@Builder @Data @NoArgsConstructor @AllArgsConstructor
public class Coffee implements Serializable {
    @Id
    @GeneratedValue // 可以设置属性 strategy = GenerationType.IDENTITY 使用数据库自增主键
    private Long id;

    private String name;

    @Column
    @Type(type = "org.jadira.usertype.moneyandcurrency.joda.PersistentMoneyAmount", // 使用小数保存，还可以改成 PersistentBigMoneyMinorAmount 使用整数保存，x100 倍
            parameters = {@org.hibernate.annotations.Parameter(name = "currencyCode", value = "CNY")})
    private Money price;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createTime;

    @UpdateTimestamp
    private Date updateTime;
}
