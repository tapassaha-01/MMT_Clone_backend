---------------------------------------user_entity--------------------------------------

CREATE TABLE IF NOT EXISTS "public"."user_entity"
{
    id BIGINT PRIMARY KEY,
    user_name VARCHAR(255),
    email VARCHAR(255) NOT NULL UNIQUE,
    phone_no BIGINT,
    password VARCHAR(255) NOT NULL UNIQUE,
    is_admin BOOLEAN
};