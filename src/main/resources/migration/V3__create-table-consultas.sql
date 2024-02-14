create table consultas(

    id bigint not null auto_increment,
    psicologo_id bigint not null,
    paciente_id bigint not null,
    data datetime not null,

    primary key(id),
    constraint fk_consultas_psicologo_id foreign key(psicologo_id) references psicologos(id),
    constraint fk_consultas_paciente_id foreign key(paciente_id) references pacientes(id)

);