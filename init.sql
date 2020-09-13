CREATE TYPE public.e_customer_membership AS ENUM (
    'Gold',
    'Platinum',
    'Standard');

CREATE TABLE public.order
(
    id              int4         NOT NULL GENERATED ALWAYS AS IDENTITY,
    creationDate    timestamptz  NOT NULL,
    customerName    varchar(200) NOT NULL,
    orderTotal      numeric(10, 3),
    membershipLevel e_customer_membership,
    CONSTRAINT order_pkey PRIMARY KEY (id)
);

/* Generate 1 Million Rows */
insert into public.order (creationDate, customerName, orderTotal, membershipLevel)
select current_timestamp,
       md5(random()::text),
       1000 * random()::numeric(10, 3),
       'Standard'
from generate_series(1, 1000000) s(i);