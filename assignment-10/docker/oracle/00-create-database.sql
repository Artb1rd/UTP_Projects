CREATE PLUGGABLE DATABASE UTP10
    ADMIN USER UTP10
    IDENTIFIED BY UTP10
    ROLES = (DBA)
    FILE_NAME_CONVERT = ('/pdbseed/', '/utp10/');

ALTER SYSTEM SET LOCAL_LISTENER='utp10-listener';

ALTER PLUGGABLE DATABASE ALL OPEN;
ALTER PLUGGABLE DATABASE ALL SAVE STATE;

GRANT CREATE SESSION TO UTP10;