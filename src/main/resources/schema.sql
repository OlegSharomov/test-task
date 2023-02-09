DROP TABLE IF EXISTS sk_example_table;
CREATE TABLE sk_example_table
(
    id SERIAL,
    obj JSONB NOT NULL,
    PRIMARY KEY (id)
);