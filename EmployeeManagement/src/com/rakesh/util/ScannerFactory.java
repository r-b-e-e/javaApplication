package com.rakesh.util;

import java.util.Scanner;

// This class creates only one Scanner Object and used throughout the application
public class ScannerFactory {
	static final Scanner scanner = new Scanner(System.in);

	public static Scanner getScannerObject() {
		return scanner;
	}
}
