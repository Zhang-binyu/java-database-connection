JdbcTemplate：

用途：JdbcTemplate 是 Spring 框架的一部分，它提供了一种相对较低级别的方式来执行 SQL 查询和更新操作。你需要手动编写 SQL 语句，并使用 JdbcTemplate 执行这些语句。这对于需要更多控制权和灵活性的数据库操作非常有用。

工作方式：你需要创建 JdbcTemplate Bean 并配置数据源（DataSource）。然后，你可以使用 JdbcTemplate 实例执行 SQL 查询和更新操作，手动处理结果集和参数绑定。

优点：JdbcTemplate 提供了直接的 SQL 控制权，适用于需要高度自定义的数据库操作。

缺点：需要手动编写 SQL 查询和更新语句，可能会导致较多的样板代码，而且不够抽象化。