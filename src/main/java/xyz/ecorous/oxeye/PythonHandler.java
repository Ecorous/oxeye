package xyz.ecorous.oxeye;

import org.python.core.PyException;
import org.python.util.PythonInterpreter;

import java.io.File;

public class PythonHandler
{

	public static void runPython(String code) throws PyException {
		PythonInterpreter interpreter = new PythonInterpreter();
		//interpreter.setOut(System.out);
		//interpreter.setErr(System.err);
		interpreter.exec(code);
	}
	public static void runPythonFile(File file) throws PyException {
		PythonInterpreter interpreter = new PythonInterpreter();
		//interpreter.setOut(System.out);
		//interpreter.setErr(System.err);
		interpreter.execfile(file.getAbsolutePath());
	}
}
