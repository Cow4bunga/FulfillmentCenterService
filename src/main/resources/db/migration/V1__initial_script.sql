BEGIN;

CREATE TABLE public.products
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    product_name            VARCHAR(255) NOT NULL,
    status             VARCHAR(50)  NOT NULL CHECK (status IN ('UNFULFILLABLE', 'SELLABLE', 'INBOUND')),
    fulfillment_center VARCHAR(255),
    qty                INTEGER,
    value              DOUBLE PRECISION
);

CREATE INDEX idx_products_status ON public.products (status);
CREATE INDEX idx_products_fulfillment_center ON public.products (fulfillment_center);

COMMIT;