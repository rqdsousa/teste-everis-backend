DROP TABLE IF EXISTS ranking;

CREATE TABLE players (
  id LONG AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  points INT NOT NULL
);
