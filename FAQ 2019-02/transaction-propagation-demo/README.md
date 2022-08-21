# Transaction Propagation Demo
事务传播属性 Demo

- Propagation.REQUIRES_NEW 不会被外部调用方法的回调而影响
- Propagation.NESTED 会被外部调用方法的回调而影响（外部回滚，自己也回滚）
