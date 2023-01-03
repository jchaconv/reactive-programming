package pe.com.reactive.sec11Sinks;

import pe.com.reactive.sec11Sinks.assignment.SlackMember;
import pe.com.reactive.sec11Sinks.assignment.SlackRoom;
import pe.com.reactive.util.Util;

public class SlackDemo {

    public static void main(String[] args) {

        SlackRoom slackRoom = new SlackRoom("reactor");

        SlackMember julio = new SlackMember("Julio");
        SlackMember cesar = new SlackMember("Cesar");
        SlackMember aaron = new SlackMember("Aaron");

        slackRoom.joinRoom(julio);
        slackRoom.joinRoom(cesar);

        julio.says("Hi all ...");
        Util.sleepSeconds(3);

        cesar.says("Hey!");
        julio.says("I simply wanted to say hi...");
        Util.sleepSeconds(3);

        slackRoom.joinRoom(aaron);
        aaron.says("Hey guys ... glad to be here...");

    }

}
