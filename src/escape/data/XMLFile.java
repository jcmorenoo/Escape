package escape.data;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import escape.gameworld.GameWorld;
import escape.gameworld.Item;
import escape.gameworld.Player;
import escape.gameworld.Room;
import escape.gameworld.Player.Direction;

public class XMLFile {

	public void saveGame(GameWorld game, Player p) {
		//if(game==null) System.out.println("Game null");
		//Player p = game.getPlayers().get(1);
		if(p==null){
			System.out.println("Player null");
			
		}
		try {
			StringWriter stringWriter = new StringWriter();
			XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
			XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
			xMLStreamWriter.writeStartDocument();
			xMLStreamWriter.writeStartElement("Players");
			
				xMLStreamWriter.writeStartElement("Player");
				xMLStreamWriter.writeStartElement("ID");
				System.out.print(p.getId());
				xMLStreamWriter.writeCharacters("" + p.getId());
				xMLStreamWriter.writeEndDocument();
				xMLStreamWriter.writeStartElement("Name");
				xMLStreamWriter.writeCharacters(p.getName());
				xMLStreamWriter.writeEndDocument();
				xMLStreamWriter.writeStartElement("Room");
				xMLStreamWriter.writeCharacters(p.getRoom().getName());
				xMLStreamWriter.writeEndDocument();
				for (Item item : p.getItems()) {
					xMLStreamWriter.writeStartElement("Item");
					xMLStreamWriter.writeCharacters(item.getName());
					xMLStreamWriter.writeEndDocument();
				}
				xMLStreamWriter.writeStartElement("Direction");
				xMLStreamWriter.writeCharacters(p.getDirection().toString());
				xMLStreamWriter.writeEndDocument();
				xMLStreamWriter.writeEndDocument();
			
			xMLStreamWriter.writeEndDocument();
			xMLStreamWriter.writeEndDocument();
			xMLStreamWriter.flush();
			xMLStreamWriter.close();
			String xmlString = stringWriter.getBuffer().toString();
			stringWriter.close();
			try {
				BufferedWriter out = new BufferedWriter(new FileWriter("SavedGame.xml"));
				out.write(xmlString);
				out.close();
			} catch (IOException e) {
				System.out.println("Exception ");
			}
		} catch (XMLStreamException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public GameWorld loadGame(String fileName) {
		GameWorld game = new GameWorld("0");// I don't know what ID is so I just
											// put random number.
		boolean bID = false;
		boolean bName = false;
		boolean bRoom = false;
		boolean bItem = false;
		boolean bDirection = false;
		try {
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(fileName));
			int Id = 0;
			String name = null;
			Room room;
			Player p = null;
			while (eventReader.hasNext()) {
				//System.out.println(eventReader.next());
				XMLEvent event = eventReader.nextEvent();
				switch (event.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					StartElement start = event.asStartElement();
					String qname = start.getName().getLocalPart();
					if (qname.equalsIgnoreCase("ID")) {
						bID = true;
					} else if (qname.equalsIgnoreCase("Name")) {
						bName = true;
					} else if (qname.equalsIgnoreCase("Room")) {
						bRoom = true;
					} else if (qname.equalsIgnoreCase("Item")) {
						bItem = true;
					} else if (qname.equalsIgnoreCase("Direction")) {
						bDirection = true;
					} else {
						break;
					}
					break;
				case XMLStreamConstants.CHARACTERS:
					Characters character = (Characters)event.asCharacters();
					if (bID) {
						Id = Integer.parseInt(character.getData());
						bID = false;
					} else if (bName) {
						name = character.getData();
						bName = false;
					} else if (bRoom) {
						room = game.getRooms().get(character.getData());
						p = new Player(Id, name, room);
						game.addPlayer(p);
						bRoom = false;
					} else if (bItem) {
						Item item = game.getItem(character.getData());
						p.pickUpItem(item);
						bItem = false;
					} else if (bDirection) {
						Direction direction = Direction.valueOf(character.getData());
						p.setDirection(direction);
						bDirection = false;
					}
					break;
				case XMLStreamConstants.END_ELEMENT:
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
		return game;
	}
}