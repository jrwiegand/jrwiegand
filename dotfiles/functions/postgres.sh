## function to update the owner of a psql database file
pdbo() {
	find "$1" -type f -exec sed -i "" "s/ Owner: $2/ Owner: $3/g" {}\;
	find "$1" -type f -exec sed -i "" "s/ $2;/$3;/g" {}\;
}

## psql function to clear the database to a usable state
pdbr() { dropdb "$1" && createdb "$1" && psql "$1" -f "$2"; }

## psql function to create a new db with the given .sql file
pdbn() { createdb "$1" && psql "$1" -f "$2"; }
