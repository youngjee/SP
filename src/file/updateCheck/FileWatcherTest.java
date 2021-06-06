package file.updateCheck;

import java.io.File;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FileWatcherTest {
	public static void main( String args[] )
    {
        // monitor a single file
        TimerTask task = new FileWatcher( new File( "./aaa.txt" ) ) {
            @Override
            protected void onChange( File file )
            {
                // here we code the action on a change
                System.out.println( "File " + file.getName( ) + " have been changed !" );
            }
        };
        
        Timer timer = new Timer( );
        // repeat the check every second
        timer.schedule( task , new Date( ) , 1000 );
    }
}
