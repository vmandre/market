-- Table: public.cargo
CREATE SEQUENCE cargo_cod_seq START 1;

CREATE TABLE public.cargo
(
  cod bigint NOT NULL DEFAULT nextval('cargo_cod_seq'::regclass),
  descricao character varying(128) NOT NULL,
  CONSTRAINT cargo_pkey PRIMARY KEY (cod)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cargo
  OWNER TO postgres;

-- Table: public.loja
CREATE SEQUENCE loja_cod_seq START 1;

CREATE TABLE public.loja
(
  cod bigint NOT NULL DEFAULT nextval('loja_cod_seq'::regclass),
  nome character varying(64) NOT NULL,
  CONSTRAINT loja_pkey PRIMARY KEY (cod)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.loja
  OWNER TO postgres;
  
-- Table: public.setor
CREATE SEQUENCE setor_cod_seq START 1;

CREATE TABLE public.setor
(
  cod bigint NOT NULL DEFAULT nextval('setor_cod_seq'::regclass),
  descricao character varying(64) NOT NULL,
  CONSTRAINT setor_pkey PRIMARY KEY (cod)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.setor
  OWNER TO postgres;
  
-- Table: public.funcionario
CREATE SEQUENCE funcionario_cod_seq START 1;

CREATE TABLE public.funcionario
(
  cod bigint NOT NULL DEFAULT nextval('funcionario_cod_seq'::regclass),
  matricula bigint NOT NULL,
  nome character varying(64) NOT NULL,
  data_admissao date NOT NULL,
  cod_loja bigint NOT NULL,
  cod_cargo bigint NOT NULL,
  ativo boolean NOT NULL DEFAULT TRUE, 
  data_nascimento date NOT NULL,
  cpf bigint NOT NULL,
  telefone character varying(16) NOT NULL,
  celular character varying(16) NOT NULL, 
  email character varying(64) NOT NULL,
  CONSTRAINT funcionario_pkey PRIMARY KEY (cod),
  CONSTRAINT funcionario_cod_loja_fkey FOREIGN KEY (cod_loja)
      REFERENCES public.loja (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT funcionario_cod_cargo_fkey FOREIGN KEY (cod_cargo)
      REFERENCES public.cargo (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.funcionario
  OWNER TO postgres;

-- Table: public.funcionario_setor
CREATE SEQUENCE funcionario_setor_cod_seq START 1;

CREATE TABLE public.funcionario_setor
(
  cod bigint NOT NULL DEFAULT nextval('funcionario_setor_cod_seq'::regclass),
  cod_setor bigint NOT NULL,
  cod_funcionario bigint NOT NULL,
  data_inicio date NOT NULL,
  data_fim date,
  CONSTRAINT funcionario_setor_pkey PRIMARY KEY (cod),
  CONSTRAINT funcionario_setor_cod_funcionario_fkey FOREIGN KEY (cod_funcionario)
      REFERENCES public.funcionario (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT funcionario_setor_cod_setor_fkey FOREIGN KEY (cod_setor)
      REFERENCES public.setor (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.funcionario_setor
  OWNER TO postgres;

-- Table: public.historico_ferias
CREATE SEQUENCE historico_ferias_cod_seq START 1;

CREATE TABLE public.historico_ferias
(
  cod bigint NOT NULL DEFAULT nextval('historico_ferias_cod_seq'::regclass),
  cod_funcionario bigint NOT NULL,
  data_solicitacao date NOT NULL,
  data_inicio date NOT NULL,
  data_fim date NOT NULL,
  status character varying(32) NOT NULL,
  observacao character varying(256),
  CONSTRAINT historico_ferias_pkey PRIMARY KEY (cod),
  CONSTRAINT historico_ferias_cod_funcionario_fkey FOREIGN KEY (cod_funcionario)
      REFERENCES public.funcionario (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.historico_ferias
  OWNER TO postgres;

-- Table: public.holerite
CREATE SEQUENCE holerite_cod_seq START 1;

CREATE TABLE public.holerite
(
  cod bigint NOT NULL DEFAULT nextval('holerite_cod_seq'::regclass),
  cod_funcionario bigint NOT NULL,
  data_geracao date NOT NULL,
  path_arquivo character varying(256) NOT NULL,
  CONSTRAINT holerite_pkey PRIMARY KEY (cod),
  CONSTRAINT holerite_cod_funcionario_fkey FOREIGN KEY (cod_funcionario)
      REFERENCES public.funcionario (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.holerite
  OWNER TO postgres;

-- Table: public.loja_setor
CREATE SEQUENCE loja_setor_cod_seq START 1;

CREATE TABLE public.loja_setor
(
  cod bigint NOT NULL DEFAULT nextval('loja_setor_cod_seq'::regclass),
  cod_loja bigint NOT NULL,
  cod_setor bigint NOT NULL,
  CONSTRAINT loja_setor_pkey PRIMARY KEY (cod),
  CONSTRAINT loja_setor_cod_loja_fkey FOREIGN KEY (cod_loja)
      REFERENCES public.loja (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT loja_setor_cod_setor_fkey FOREIGN KEY (cod_setor)
      REFERENCES public.setor (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.loja_setor
  OWNER TO postgres;

-- Table: public.noticia
CREATE SEQUENCE noticia_cod_seq START 1;

CREATE TABLE public.noticia
(
  cod bigint NOT NULL DEFAULT nextval('noticia_cod_seq'::regclass),
  titulo character varying(64) NOT NULL,
  texto character varying(2048) NOT NULL,
  data_inicio date NOT NULL,
  data_fim date NOT NULL,
  cod_loja bigint,
  CONSTRAINT noticia_pkey PRIMARY KEY (cod),
  CONSTRAINT noticia_cod_loja_fkey FOREIGN KEY (cod_loja)
	REFERENCES public.loja (cod) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.noticia
  OWNER TO postgres;

-- Table: public.vaga
CREATE SEQUENCE vaga_cod_seq START 1;

CREATE TABLE public.vaga
(
  cod bigint NOT NULL DEFAULT nextval('vaga_cod_seq'::regclass),
  data_abertura date NOT NULL,
  descricao character varying(256) NOT NULL,
  cod_cargo bigint NOT NULL,
  cod_loja bigint NOT NULL,
  CONSTRAINT vaga_pkey PRIMARY KEY (cod),
  CONSTRAINT vaga_cod_cargo_fkey FOREIGN KEY (cod_cargo)
      REFERENCES public.cargo (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT vaga_cod_loja_fkey FOREIGN KEY (cod_loja)
	REFERENCES public.loja (cod) MATCH SIMPLE
	ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.vaga
  OWNER TO postgres;

    
-- Table: public.funcionario_vaga
CREATE SEQUENCE funcionario_vaga_cod_seq START 1;

CREATE TABLE public.funcionario_vaga
(
  cod bigint NOT NULL DEFAULT nextval('funcionario_vaga_cod_seq'::regclass),
  cod_funcionario bigint NOT NULL,
  cod_vaga bigint NOT NULL,
  CONSTRAINT funcionario_vaga_pkey PRIMARY KEY (cod),
  CONSTRAINT funcionario_vaga_cod_funcionario_fkey FOREIGN KEY (cod_funcionario)
      REFERENCES public.funcionario (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT funcionario_vaga_cod_vaga_fkey FOREIGN KEY (cod_vaga)
      REFERENCES public.vaga (cod) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.funcionario_vaga
  OWNER TO postgres;