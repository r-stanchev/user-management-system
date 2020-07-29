INSERT INTO users (id,first_name, last_name, email, date_of_birth) VALUES
('1','Ross', 'Geller', 'geller_r@gmail.com', STR_TO_DATE('08-08-1985', '%d-%m-%Y')),
('2','Chandler', 'Bing', 'chandler@gmail.com', STR_TO_DATE('15-03-1997', '%d-%m-%Y')),
('3','Monica', 'Geller', 'geller_m@gmail.com', STR_TO_DATE('22-01-1997', '%d-%m-%Y')),
('4','Rachel', 'Green', 'green@gmail.com', STR_TO_DATE('02-12-1981', '%d-%m-%Y')),
('5','Phoebe', 'Buffay', 'phoebe@gmail.com', STR_TO_DATE('02-12-1982', '%d-%m-%Y')),
('6','Joey', 'Tribbiani', 'joey@gmail.com', STR_TO_DATE('30-07-1997', '%d-%m-%Y'))
ON DUPLICATE KEY UPDATE id=id;