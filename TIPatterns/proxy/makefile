# From Thinking in Patterns (with Java) by Bruce Eckel
# At http://www.BruceEckel.com
# (c)2003 Bruce Eckel 
# Copyright notice in Copyright.txt
# Automatically-generated MAKEFILE 
# For examples in directory .\proxy
# using the JDK 1.4 compiler
# Invoke with: make

HOME := ../

ifndef MAKECMDGOALS
MAKECMDGOALS := javac
endif

# Command.com is too weak to build this under Windows NT/2000:
ifeq ($(OS),Windows_NT)
COMSPEC=$(SYSTEMROOT)\system32\cmd.exe
endif

ifneq ($(MAKECMDGOALS),clean)
include $(HOME)/$(MAKECMDGOALS).mac
endif

.SUFFIXES : .class .java
.java.class :
	$(JVC) $(JVCFLAGS) $<

javac: \
	ProxyDemo.class \
	PoolManager.class \
	ConnectionPoolProxyDemo.class

jikes: \
	ProxyDemo.class \
	PoolManager.class \
	ConnectionPoolProxyDemo.class

clean:
ifeq ($(notdir $(SHELL)),COMMAND.COM)
	del *.class
else
	rm -f *.class
endif


ProxyDemo.class: ProxyDemo.java 
	$(JVC) $(JVCFLAGS) $<
	java proxy.ProxyDemo
PoolManager.class: PoolManager.java 

ConnectionPoolProxyDemo.class: ConnectionPoolProxyDemo.java 
	$(JVC) $(JVCFLAGS) $<
	java proxy.ConnectionPoolProxyDemo
