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