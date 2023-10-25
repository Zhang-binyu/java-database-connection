Spring Data JPA：

用途：Spring Data JPA 是 Spring Data 项目的一部分，它是 JPA（Java Persistence API）的抽象层，提供了更高级别的、基于对象的数据库操作。你可以使用 JPA 注解来定义实体类，而不需要编写大量的 SQL 查询语句。

工作方式：Spring Data JPA 使用 JPA 的规范，你只需定义仓库接口（Repository Interface），Spring Data JPA 会自动生成查询方法的实现，不需要编写具体的 SQL。它还支持自定义查询方法。

优点：抽象化了数据库操作，减少了样板代码，支持面向对象的编程。可以轻松实现复杂的查询操作。

缺点：可能会对性能有一些影响，因为它是一个高级别的抽象，不如手动编写 SQL 查询那么灵活。