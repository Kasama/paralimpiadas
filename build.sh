#!/bin/bash

OUTPUT_FOLDER='../gen'
SCHEMA='../paralimpicos.yml.erb'
GEN_FOLDER='sqlGen'
PACKAGE='br.usp.icmc.paralimpiadas.database'
GENERATOR_REPO='http://github.com/Kasama/sqlGenerator'

if [[ ! -d GEN_FOLDER ]]; then
	git clone $GENERATOR_REPO $GEN_FOLDER
fi

cd $GEN_FOLDER
rm ../src/br/usp/icmc/paralimpiadas/database

ruby main.rb $SCHEMA $OUTPUT_FOLDER $PACKAGE

ln -s ../../../../../gen/br/usp/icmc/paralimpiadas/database ../src/br/usp/icmc/paralimpiadas/database
