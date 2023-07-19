ARGS = scenario.txt
MAIN_CLASS = src.Simulator

all: run

run: 
	@printf "\rCompiling ... ";
	@javac src/*.java src/Aircraft/*.java
	@printf "\rStarting the simulation ${ARGS}"
	@echo ""
	@echo "*----------------------------------*"
	@java ${MAIN_CLASS} ${ARGS}

clean:
	rm -rf */*.class */*/*.class

re: clean all

.PHONY: all run clean
