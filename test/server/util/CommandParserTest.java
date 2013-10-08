package server.util;

import org.junit.Test;
import server.commands.BaseCommand;
import server.commands.Command;
import server.commands.Say;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Giymo11
 * Date: 07.10.13
 * Time: 09:24
 * Tests the command parser functionality.
 */
public class CommandParserTest {
    @Test
    public void testDefaultParse() {
        String message = "test string 1000";
        Command com = new Say(message.split(" "));
        assertEquals(com, CommandParser.parse(message));
    }

    @Test
    public void testSpecificParse() {
        String message = "/asdf a spamming user ";
        Command com = new BaseCommand(message.split(" "));
        assertEquals(com, CommandParser.parse(message));
    }

    @Test
    public void testCommandEquals() {
        Command com = new BaseCommand("/hurr durr furr zurr".split(" "));
        Command com2 = new BaseCommand("/hurr durr furr zurr".split(" "));
        assertEquals(com, com2);
    }
}
