#!/bin/bash

OUTPUT_FOLDER='../gen'
SCHEMA='../paralimpicos.yml.erb'
GEN_FOLDER='sqlGen'
PACKAGE='br.usp.icmc.paralimpiadas.database'

cd $GEN_FOLDER
rm ../src/br/usp/icmc/paralimpiadas/database

ruby main.rb $SCHEMA $OUTPUT_FOLDER $PACKAGE

ln -s ../../../../../gen/br/usp/icmc/paralimpiadas/database ../src/br/usp/icmc/paralimpiadas/database
