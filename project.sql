drop table students;
drop table professors;
drop table courses;
drop table courses_sections;
drop table courses_prerequisites;
drop table professors_courses;
drop table students_grades;

create table students(
    sid             int             constraint std_pk primary key,
    name            varchar2(50)    constraint std_name_nn not null,
    sex             char            constraint std_sex_chk check (sex in ('M','F')),
    start_sem       varchar2(25)    constraint std_sem_chk check (REGEXP_LIKE(start_sem,'[Fall|Spring|Summer] [0-9][0-9][0-9][0-9]')),
    credits         int             constraint std_credits_chk check (credits between 0 and 200)
);

insert into students values (70000,'Braiden Lane','M','Fall 2020',0);
insert into students values (71000,'Rajveer Whitehouse','M','Fall 2020',0);
insert into students values (72000,'Matt Goodman','M','Fall 2020',0);
insert into students values (73000,'Reyansh Palmer','M','Fall 2020',0);
insert into students values (74000,'Aahil Jaramillo','M','Fall 2020',0);
insert into students values (75000,'Najma Galvan','F','Fall 2020',0);
insert into students values (76000,'Rhona Woods','F','Fall 2020',0);
insert into students values (77000,'Maria Shaffer','F','Fall 2020',0);
insert into students values (78000,'Hafsah Gough','F','Fall 2020',0);
insert into students values (79000,'Mathilda Conway','F','Fall 2020',0);
insert into students values (80000,'Tester McTester','M','Fall 2019',0);

select * from students;

create table professors(
    pid             int             constraint prf_pk primary key,
    name            varchar2(50)    constraint prf_name_nn not null,
    sex             char            constraint prf_sex_chk check (sex in ('M','F')),
    college         varchar2(4)     constraint prf_dept_chk check (college in ('CAAD','SBA','CAS','CEN')),
    age             int             constraint prf_age_chk check (age between 0 and 100)
);

insert into professors values (10,'Jo Romero','M','CEN',35);
insert into professors values (20,'Evelina Mason','F','CEN',45);
insert into professors values (30,'Sam Wilkins','M','CAS',37);
insert into professors values (40,'Harry Freeman','M','CAS',40);
insert into professors values (50,'Tayah Allen','M','SBA',33);

create table courses(
    course_code     varchar2(10)    constraint crs_code_pk primary key,
    course_name     varchar2(50)    constraint crs_name_nn not null,
    credits         int             constraint crs_credits_chk check (credits between 1 and 4)
);

insert into courses values ('COE101','Introduction to Engineering 1',3);
insert into courses values ('COE102','Introduction to Engineering 2',4);
insert into courses values ('COE108','Engineering Project',2);
insert into courses values ('CAS101','Introduction to Science 1',3);
insert into courses values ('CAS102','Introduction to Science 2',4);
insert into courses values ('CAS108','Science Project',2);
insert into courses values ('CAAD101','Introduction to Design 1',3);
insert into courses values ('CAAD102','Introduction to Design 2',4);
insert into courses values ('CAAD108','Design Project',2);

create table courses_sections(
    crn             int             constraint crs_sec_crn_pk primary key,
    course_code     varchar2(10)    constraint crs_sec_code_fk references courses(course_code),
    pid             int             constraint crs_sec_pid_fk references professors(pid),
    semester        varchar2(25)    constraint crs_sec_sem_chk check (REGEXP_LIKE(semester,'[Fall|Spring|Summer] [0-9][0-9][0-9][0-9]')),
    location        varchar2(50)    constraint crs_sec_loc_chk check (location in ('EB','SB','BB','DB')),
    time            varchar2(50)    constraint crs_sec_time_nn not null
);

insert into courses_sections values (11011,'COE101',10,'Summer 2021','EB','8AM - 9AM');
insert into courses_sections values (11012,'COE101',20,'Summer 2021','EB','10AM - 11AM');
insert into courses_sections values (12011,'COE102',10,'Fall 2021','EB','12PM - 1PM');
insert into courses_sections values (12012,'COE102',20,'Fall 2021','EB','2PM - 3PM');

create table courses_prerequisites(
    course_code     varchar2(10)    constraint crs_preq_crs_fk references courses(course_code),
    prereq_code     varchar2(10)    constraint crs_preq_pre_fk references courses(course_code),
                                    constraint crs_preq_crn_pk primary key (course_code,prereq_code)
);

insert into courses_prerequisites values ('CEN102','COE101');
insert into courses_prerequisites values ('COE108','COE102');
insert into courses_prerequisites values ('CAS102','CAS101');
insert into courses_prerequisites values ('CAS108','CAS102');
insert into courses_prerequisites values ('CAAD102','CAAD101');
insert into courses_prerequisites values ('CAAD108','CAAD102');

create table professors_courses(
    pid             int             constraint prf_crs_pid_fk references professors(pid),
    course_code     varchar2(10)    constraint prf_crs_crs_fk references courses(course_code),
                                    constraint prf_crs_pk primary key (pid,course_code)
);

insert into professors_courses values (10,'COE101');
insert into professors_courses values (10,'COE102');
insert into professors_courses values (10,'COE108');
insert into professors_courses values (20,'COE101');
insert into professors_courses values (20,'COE102');
insert into professors_courses values (30,'CAS101');
insert into professors_courses values (30,'CAS102');
insert into professors_courses values (30,'CAS108');
insert into professors_courses values (40,'CAS101');
insert into professors_courses values (40,'CAS102');
insert into professors_courses values (50,'CAAD101');

create table students_grades(
    sid             int             constraint std_grd_sid_fk references students(sid),
    crn             int             constraint std_grd_crn_fk references courses_sections(crn),
    grade           number(5,2)     constraint std_grd_grd_chk check (grade between 0 and 100),
                                    constraint std_grd_pk primary key (sid,crn)
);

insert into students_grades values (80000,11011,90.0);
insert into students_grades values (80000,11012,87.0);
insert into students_grades values (79000,11011,79.0);
insert into students_grades values (79000,11012,77.0);

select sum(c.credits)
from courses c, courses_sections cs, students_grades sg
where sg.crn = cs.crn
and cs.course_code = c.course_code
and sg.sid = 80000;

select * from students;
select * from professors;
select * from courses;
select * from courses_sections;
select * from courses_prerequisites;
select * from professors_courses;
select * from students_grades;
