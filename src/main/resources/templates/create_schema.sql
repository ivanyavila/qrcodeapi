CREATE TABLE IF NOT EXISTS public.qrcode
(
    ulid character varying COLLATE pg_catalog."default" NOT NULL,
    code text NOT NULL,
    created_at bigint,
    read_at bigint,
    valid_until bigint,
    active boolean NOT NULL DEFAULT true,
    CONSTRAINT qrcode_pkey PRIMARY KEY (ulid),
    CONSTRAINT qrcode_code_string_key UNIQUE (code)
)