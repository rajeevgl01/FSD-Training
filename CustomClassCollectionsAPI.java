import java.util.*;

class CustomClassCollectionsAPI{
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		Computer first = new Computer("Alienware", "Black", 1000, 16, "c2thyu");

		List list = new ArrayList();

		list.add(first);
		list.add(new Computer("ASUS ROG", "black", 1000, 32, "yhfn32"));

		while(true){
			System.out.println();
			
			System.out.println("1. Add a Computer");
			System.out.println("2. Show all computers");
			System.out.println("3. Search for a computer by Brand, Color, HDDSize, RAMSize, ID");
			System.out.println("4. Delete a computer by ID");
			System.out.println("5. Update computer details by Brand, Color, HDDSize, RAMSize");			
			System.out.println("Enter the choice");
			int num = scan.nextInt();

			switch(num){
				case 1:
					scan.nextLine();

					System.out.println("Enter brand name");
					String brand1 = scan.nextLine();

					System.out.println("Enter the color");
					String color1 = scan.nextLine();

					System.out.println("Enter HDD size");
					int hddSize1 = scan.nextInt();

					System.out.println("Enter RAM Size");
					int ramSize1 = scan.nextInt();

					System.out.println("Enter Computer ID");
					String id1 = scan.nextLine();

					list.add(new Computer(brand1, color1, hddSize1, ramSize1, id1));

					System.out.println("New updated computer list");
					System.out.println(list);

					break;

				case 2:
					System.out.println("List of all the computers");
					System.out.println(list);

					break;

				case 3:
					System.out.println("Enter '1' for brand, '2' for color, '3' for hddSize, '4' for ramSize and '5' for ID ");
					int num1 = scan.nextInt();

					switch(num1){
						case 1:
							scan.nextLine();

							System.out.println("Enter the brand name");
							String brand2 = scan.nextLine();

							boolean flag = false;;

							for (Object ref : list) {
								if(brand2.equals(((Computer)ref).getBrand())){
									System.out.println(ref);
									flag = true;	
								}
							}
							if(flag == false)
								System.out.println("Not found");

							break;

						case 2:
							scan.nextLine();

							System.out.println("Enter the color");
							String color2 = scan.nextLine();

							boolean flag1 = false;

							for (Object ref : list) {
								if(color2.equals(((Computer)ref).getColor())){
									System.out.println(ref);
									flag1 = true;	
								}
							}
							if(flag1 == false)
								System.out.println("Not found");

							break;

						case 3:
							scan.nextLine();

							System.out.println("Enter the hddSize");
							Integer hddsize2 = scan.nextInt();

							boolean flag2 = false;

							for (Object ref : list) {
								if(hddsize2.equals(((Computer)ref).getHDDSize())){
									System.out.println(ref);
									flag2 = true;	
								}
							}
							if(flag2 == false)
								System.out.println("Not found");

							break;

						case 4:
							scan.nextLine();

							System.out.println("Enter the ram size");
							Integer ram2 = scan.nextInt();

							boolean flag3 = false;

							for (Object ref : list) {
								if(ram2.equals(((Computer)ref).getRAMSize())){
									System.out.println(ref);
									flag3 = true;	
								}
							}
							if(flag3 == false)
								System.out.println("Not found");

							break;

						case 5:
							scan.nextLine();

							System.out.println("Enter the ID");
							String id3 = scan.nextLine();

							boolean flag4 = false;

							for (Object ref : list) {
								if(id3.equals(((Computer)ref).getID())){
									System.out.println(ref);
									flag4 = true;	
								}
							}
							if(flag4 == false)
								System.out.println("Not found");

							break;

						default:
							try{
								throw new IllegalStateException();
							}catch(IllegalStateException e){
								e.printStackTrace();
							}
					}
					
					break;

				case 4:
					scan.nextLine();

					System.out.println("Enter the ID for the computer to be deleted");
					String id2 = scan.nextLine();

					for (Object ref : list) {
						if(id2.equals(((Computer)ref).getID())){
							list.remove((Computer)ref);
							break;
						}

					}

					System.out.println("New updated computer list");
					System.out.println(list);

					break;

				case 5:
					scan.nextLine();

					System.out.println("Enter the ID of the computer for which the details need to be updated");
					String id4 = scan.nextLine();

					boolean flag = false;

					for (Object ref : list) {
						if(id4.equals(((Computer)ref).getID())){
							System.out.println((Computer)ref);
							flag = true;
							System.out.println("Enter '1' for brand, '2' for color, '3' for hddSize, '4' for ramSize");
							int num2 = scan.nextInt();

							switch(num2){
								case 1:
									scan.nextLine();
									System.out.println("Enter the new brand name");
									String brand3 = scan.nextLine();

									((Computer)ref).setBrand(brand3);

									System.out.println(list);
									break;

								case 2:
									scan.nextLine();
									System.out.println("Enter the new color name");
									String color3 = scan.nextLine();

									((Computer)ref).setColor(color3);

									System.out.println(list);
									break;

								case 3:
									scan.nextLine();
									System.out.println("Enter the new hdd size");
									int hdd3 = scan.nextInt();

									((Computer)ref).setHDDSize(hdd3);

									System.out.println(list);
									break;

								case 4:
									scan.nextLine();
									System.out.println("Enter the new ram size");
									int ram3 = scan.nextInt();

									((Computer)ref).setRAMSize(ram3);

									System.out.println(list);
									break;
								
								default :
									try{
										throw new IllegalStateException();
									}catch(IllegalStateException e){
										e.printStackTrace();
									}

							}	
						break;

						}
					}

					if(flag ==  false)
						System.out.println("id not found");

					break;

				default :
					try{
						throw new IllegalStateException();
					}catch(IllegalStateException e){
						e.printStackTrace();
					}

			}
		}
	}
}

class Computer{
	String brand, color, id;
	int hddSize, ramSize;

	Computer(String brand, String color, int hddSize, int ramSize, String id){
		this.brand = brand;
		this.color = color;
		this.hddSize = hddSize;
		this.ramSize = ramSize;
		this.id = id;
	}

	public String getColor(){
		return this.color;
	}

	public String getBrand(){
		return this.brand;
	}

	public Integer getHDDSize(){
		return this.hddSize;
	}

	public Integer getRAMSize(){
		return this.ramSize;
	}

	public String getID(){
		return this.id;
	}

	public void setColor(String color_new){
		this.color = color_new;
	}

	public void setBrand(String brand_new){
		this.brand = brand_new;
	}

	public void setHDDSize(Integer hdd_new){
		this.hddSize = hdd_new;
	}

	public void setRAMSize(Integer ram_new){
		this.ramSize = ram_new;
	}

	@Override
	public String toString(){
		return "Computer : (" + this.getBrand() + ", " + this.getColor() + ", " + this.getHDDSize() + ", " + this.getRAMSize() + ", " + this.getID() + ")";
	}
}