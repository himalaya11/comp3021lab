package base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class Folder implements Comparable<Folder>, Serializable{

	private ArrayList<Note> notes;
	private String name;

	private static final long serialVersionUID = 1L;
	public Folder(String name) {
		this.name = name;
		notes = new ArrayList<Note>();
	}

	public void addNote(Note note){
		notes.add(note);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Note> getNotes() {
		return notes;
	}

	public void sortNotes(){
		Collections.sort(notes);
	}

	public ArrayList<Note> searchNotes(String keywords){
		String keywordsLower = keywords.toLowerCase();
		String[] strArr = keywordsLower.split(" or ");
		String strNew = String.join(",", strArr);
		String[] arrOfStr = strNew.split(" ");
		String[][] arrOfStrFinal = new String [arrOfStr.length][];
		boolean satisfy = false;
		int satisfyCount = 0;
		ArrayList<Note> keynote = new ArrayList<Note>();
		for(int i=0; i < arrOfStr.length; i++){
			arrOfStrFinal[i] = arrOfStr[i].split(",");
		}
		for (Note i: notes){
			if (i instanceof TextNote){
				satisfyCount = 0;
				TextNote textnote = (TextNote) i;
				String titleLower = textnote.getTitle().toLowerCase();
				String contentLower = "";
				if (textnote.getContent() != null){
					contentLower = textnote.getContent().toLowerCase();
				}
				for(int j=0; j < arrOfStr.length; j++){
					satisfy = false;
					for(int k=0; k < arrOfStrFinal[j].length; k++){
						if (titleLower.contains(arrOfStrFinal[j][k]) || contentLower.contains(arrOfStrFinal[j][k])){
							// System.out.println(arrOfStrFinal[j][k]);
							satisfy = true;
						}
					}
					if (satisfy){
						satisfyCount++;
					}
				}
				if (satisfyCount == arrOfStr.length){
					keynote.add(i);
				}
			}
			else {
				satisfyCount = 0;
				String titleLower = i.getTitle().toLowerCase();
				for(int j=0; j < arrOfStr.length; j++){
					satisfy = false;
					for(int k=0; k < arrOfStrFinal[j].length; k++){
						if (titleLower.contains(arrOfStrFinal[j][k])){
							// System.out.println(arrOfStrFinal[j][k]);
							satisfy = true;
						}
					}
					if (satisfy){
						satisfyCount++;
					}
				}
				if (satisfyCount == arrOfStr.length){
					keynote.add(i);
				}
			}
		}
		return keynote;
	}

	@Override
	public int compareTo(Folder o){
		return this.name.compareTo(o.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Folder))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;
		for (Note f : notes){
			if (f instanceof TextNote){
				nText++;
			}
			else if (f instanceof ImageNote){
				nImage++;
			}
	    }
		return name + ":" + nText + ":" + nImage;
	}

	public boolean removeNotes(String title) {
		   // TODO
		   // Given the title of the note, delete it from the folder.
		   // Return true if it is deleted successfully, otherwise return false.
			int index = 0;
			for (Note note: notes){
				if (note.getTitle().compareTo(title) == 0){
					notes.remove(index);
					return true;
				}
				index++;
			}
			return false;
		}

}
