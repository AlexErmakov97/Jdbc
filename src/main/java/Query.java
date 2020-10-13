public enum Query {
    CREATE_TABLE("create table if not exists user_" +
            "(" +
            "user_id serial primary key," +
            "first_name varchar(255) not null," +
            "last_name varchar(255) not null," +
            "email varchar(255) not null," +
            "phone_number varchar(15) not null" +
            ")"),

    INSERT("insert into user_ (user_id,first_name, last_name, email, phone_number) " +
            "values (default,(?), (?), (?), (?));"),
    SELECT("SELECT * FROM user_ order by user_id asc;"),
    UPDATE("update user_ set phone_number = '00000000000' where user_id = (?)"),
    DELETE("delete from user_ where user_id = (?)");

    String QUERY;

    Query(String QUERY) {
        this.QUERY = QUERY;
    }
}
