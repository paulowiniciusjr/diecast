ALTER TABLE diecast_vehicle
ADD COLUMN user_id BIGINT NULL;

ALTER TABLE diecast_vehicle
ADD CONSTRAINT fk_vehicle_user
FOREIGN KEY (user_id)
REFERENCES users(id)
ON DELETE CASCADE;
