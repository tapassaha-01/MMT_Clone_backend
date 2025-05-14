CREATE TABLE IF NOT EXISTS "public"."flights_details"
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    planeCompanyName VARCHAR(100) NOT NULL,
    departureDate DATE NOT NULL,
    arrivalDate DATE NOT NULL,
    startFrom VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    travelTime DATETIME NOT NULL,
    price DOUBLE NOT NULL,
    flightClass VARCHAR(50),
    fairType VARCHAR(50)
);