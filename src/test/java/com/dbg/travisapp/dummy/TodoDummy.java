package com.dbg.travisapp.dummy;

import com.dbg.travisapp.model.Todo;
import org.apache.logging.log4j.util.Strings;

public class TodoDummy {

    public static Todo create() {
        return create(Strings.EMPTY, Boolean.FALSE);
    }

    public static Todo create(String title, Boolean done) {
        final Todo todo = new Todo();
        todo.setTitle(title);
        todo.setDone(done);
        return todo;
    }

}
