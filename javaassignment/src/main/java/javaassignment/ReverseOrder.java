package javaassignment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ReverseOrder {

	// get logger
	Logger logger = Logger.getLogger(ReverseOrder.class.getName());
	
	public static void main(String[] args) {
		new ReverseOrder().reverseOrder();
	}

	public void reverseOrder() {
		logger.setLevel(Level.INFO);
		logger.log(Level.INFO, "Processing in reverseOrder method::");
		try (Stream<String> steam = Files.lines(Paths.get("src/main/resources/sample.txt"));
				BufferedWriter writer = Files.newBufferedWriter(Paths.get("src/main/resources/output.txt"));) {
			List<String> list = steam.collect(Collectors.toCollection(ArrayList::new));
			list.forEach(str -> {
				StringBuilder sb = new StringBuilder();
			    Stream.of(str)
	            .map(w -> w.split(" ")).flatMap(Arrays::stream)
	            .collect(Collectors.toCollection(LinkedList::new))
	            .descendingIterator().forEachRemaining(token -> sb.append(token+ " "));
				try {
					writer.write(sb.toString() + "\n");
				} catch (IOException e) {
					logger.log(Level.SEVERE, e.getMessage() , e);
				}
			});
			logger.log(Level.INFO, "Processing completed and output written in file::");
		} catch (Exception ex) {
			logger.log(Level.SEVERE, ex.getMessage() , ex);
		}
	}

}
