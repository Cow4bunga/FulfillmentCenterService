BEGIN;

INSERT INTO public.products (product_name, status, fulfillment_center, qty, value)
VALUES ('p1', 'SELLABLE', 'fc5', 4, 400),
       ('p2', 'UNFULFILLABLE', 'fc3', 5, 550),
       ('p3', 'INBOUND', 'fc4', 15, 1800),
       ('p5', 'SELLABLE', 'fc1', 22, 3080),
       ('p10', 'INBOUND', 'fc5', 15, 2850),
       ('p1', 'UNFULFILLABLE', 'fc2', 10, 1000),
       ('p8', 'SELLABLE', 'fc1', 5, 850),
       ('p3', 'SELLABLE', 'fc2', 2, 240),
       ('p5', 'SELLABLE', 'fc5', 9, 1260),
       ('p3', 'SELLABLE', 'fc4', 25, 3000),
       ('p1', 'SELLABLE', 'fc5', 30, 3000),
       ('p5', 'UNFULFILLABLE', 'fc2', 7, 980),
       ('p7', 'UNFULFILLABLE', 'fc3', 4, 640),
       ('p8', 'INBOUND', 'fc1', 2, 340),
       ('p9', 'INBOUND', 'fc5', 17, 3060),
       ('p10', 'SELLABLE', 'fc4', 28, 5320),
       ('p9', 'SELLABLE', 'fc2', 10, 1800),
       ('p6', 'UNFULFILLABLE', 'fc3', 9, 1350),
       ('p9', 'INBOUND', 'fc1', 23, 4140),
       ('p2', 'INBOUND', 'fc4', 21, 2310),
       ('p4', 'UNFULFILLABLE', 'fc2', 19, 2470),
       ('p3', 'SELLABLE', 'fc5', 8, 960),
       ('p2', 'SELLABLE', 'fc2', 18, 1980),
       ('p5', 'UNFULFILLABLE', 'fc3', 13, 1820),
       ('p7', 'INBOUND', 'fc1', 7, 1120),
       ('p9', 'SELLABLE', 'fc4', 15, 2700),
       ('p1', 'INBOUND', 'fc5', 6, 600),
       ('p5', 'SELLABLE', 'fc2', 2, 280),
       ('p6', 'UNFULFILLABLE', 'fc1', 6, 900);

COMMIT;