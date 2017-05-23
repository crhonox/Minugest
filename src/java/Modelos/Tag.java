/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelos;

/**
 *
 * @author crhonox
 */
public class Tag {

	public int id;
	public String tagName;
        public String idMinuta;

    public String getIdMinuta() {
        return idMinuta;
    }

    public void setIdMinuta(String idMinuta) {
        this.idMinuta = idMinuta;
    }

   

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

	//getter and setter methods
        
        public Tag() {  
        }
        
        public Tag(String idMinuta){
            this.idMinuta=idMinuta;
        }
        
	public Tag(int id, String tagName) {
		this.id = id;
		this.tagName = tagName;
	}

}