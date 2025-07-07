cmake_minimum_required(VERSION 3.18.1)
project("newprojectforhamza")

add_library( # Name of library
        native-lib

        # SHARED means it will compile to a .so file
        SHARED

        # Your source file
        native-lib.cpp )

find_library( # Define the log library for Android logging
        log-lib
        log )

target_link_libraries( # Link your native library with system libraries
        native-lib
        ${log-lib} )
