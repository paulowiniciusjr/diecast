CREATE TABLE diecast_vehicle (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    scale VARCHAR(50) NOT NULL,
    vehicle_brand VARCHAR(255) NOT NULL,
    vehicle_diecast_brand VARCHAR(255) NOT NULL,
    color VARCHAR(100) NOT NULL
);
