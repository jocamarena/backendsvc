insert ignore into user
(id, account_non_expired, account_non_locked, credentials_non_expired, enabled, email, first_name, last_name, username, password, created_date)
values
    ( 1, true, true, true, true, 'jose.j.camarena@gmail.com', 'Jose', 'Camarena', 'jcamarena', 'Clairdel803!', '2023-06-26 17:05:00'),
    ( 2, true, true, true, true, 'kendraorng@gmail.com', 'Kendra', 'Camarena', 'korng', 'Clairdel803!', '2023-06-26 17:05:00'),
    ( 3, true, true, true, true, 'default@gmail.com', 'default', 'default', 'default', 'default', '2023-06-26 17:05:00');