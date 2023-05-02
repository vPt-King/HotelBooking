package HotelBooking.Entity;

public class Facilities {

		private int id;
		private String icon;
		private String name;
		private String description;
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public Facilities(int id, String icon, String name, String description) {
			this.id = id;
			this.icon = icon;
			this.name = name;
			this.description = description;
		}
		public Facilities(int id, String name) {
			this.id = id;
			this.name = name;
		}
		public Facilities() {

		}
	}

