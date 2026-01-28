import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Book> listBook = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String msg = """
            Chương trình quản lý sách
            1. Thêm 1 cuốn sách
            2. Xóa 1 cuốn sách
            3. Thay đổi sách
            4. Xuất thông tin
            5. Tìm sách Lập trình
            6. Lấy sách tối đa theo giá
            7. Tìm kiếm theo tác giả
            0. Thoát
            Chọn chức năng:  """;

        int choose = 0;
        do {
            System.out.printf(msg);
            choose = sc.nextInt();
            switch (choose) {
                case 1 -> {
                    Book newBook = new Book();
                    newBook.input();
                    listBook.add(newBook);
                }
                case 2 -> {
                    System.out.print("Nhập vào mã sách cần xóa: ");
                    int bookid = sc.nextInt();
                    Book find = listBook.stream().filter(b -> b.getId() == bookid).findFirst().orElseThrow();
                    listBook.remove(find);
                    System.out.print("Đã xóa sách thành công");
                }
                case 3 -> {
                    System.out.print("Nhập vào mã sách cần điều chỉnh: ");
                    int bookid = sc.nextInt();
                    Book find = listBook.stream().filter(b -> b.getId() == bookid).findFirst().orElseThrow();
                }
                case 4 -> {
                    System.out.println("\n Xuất thông tin danh sách ");
                    listBook.forEach(b -> b.output());
                }
                case 5 -> {
                    List<Book> list5 = listBook.stream()
                            .filter(u -> u.getTitle().toLowerCase().contains("lập trình"))
                            .toList();
                    list5.forEach(Book::output);
                }
                case 6 -> {
                    System.out.print("Nhập mức giá P: ");
                    long p = sc.nextLong();
                    System.out.print("Nhập số lượng sách muốn lấy K: ");
                    int k = sc.nextInt();

                    System.out.println("Danh sách " + k + " cuốn sách có giá <= " + p + ":");
                    listBook.stream()
                            .filter(b -> b.getPrice() <= p)
                            .limit(k)
                            .forEach(Book::output);
                }
                case 7 -> {
                    System.out.println("Nhập tên các tác giả, bấm 0 để dừng: ");
                    Set<String> authorSet = new HashSet<>();
                    sc.nextLine();

                    while (true) {
                        String name = sc.nextLine().trim();
                        if (name.equals("0")) {
                            break;
                        }
                        if (!name.isEmpty()) {
                            authorSet.add(name);
                        }
                    }

                    for (String author : authorSet) {
                        System.out.println("Sách của tác giả: " + author);

                        listBook.stream()
                                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                                .forEach(Book::output);
                    }
                }
            }
        } while (choose!=0);
    }
}