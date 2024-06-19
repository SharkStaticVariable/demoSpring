CREATE TABLE IF NOT EXISTS accounts(
    id SERIAL PRIMARY KEY,
    balance DECIMAL,
    isActive BOOLEAN,
    isLocked BOOLEAN
);
CREATE TABLE IF NOT EXISTS users(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    age INT,
    phoneNumber VARCHAR,
    address VARCHAR,
    documentNumber VARCHAR,
    email VARCHAR,
    accounts_id INTEGER REFERENCES accounts(id)
);

ALTER TABLE users
add column password varchar ;

ALTER TABLE users
    add column username varchar ;

CREATE TABLE roles(
    id SERIAL PRIMARY KEY,
    name varchar(100) not null
);

CREATE TABLE users_roles(
  user_id INT NOT NULL,
  role_id INT NOT NULL,
FOREIGN KEY(user_id) REFERENCES users(id),
FOREIGN KEY (role_id) REFERENCES roles(id),
UNIQUE (user_id, role_id)
);

