package file.dir;

import java.io.File;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

//하위의 directory와 file을 찾음
public class FileWorkerTest {

	public static void main(String[] args) throws IOException {
		File root = new File("d:/");
		TestWalker walker = new TestWalker();
		Files.walkFileTree(root.toPath(), walker);
	}

	public static class TestWalker extends SimpleFileVisitor<Path> {

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			return super.postVisitDirectory(dir, exc);
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			System.out.println("D:" + dir);
			return super.preVisitDirectory(dir, attrs);
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			System.out.println("F:" + file);
			if (file.getFileName().toString().equals("EULA.java")) {
				return FileVisitResult.TERMINATE;
			}
			return super.visitFile(file, attrs);
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			if (exc instanceof AccessDeniedException) {
				return FileVisitResult.SKIP_SUBTREE;
			}
			return super.visitFileFailed(file, exc);
		}
	}

}
