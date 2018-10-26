# MyRoomDB
This project aims to show the implementation of Room Database.

## Getting Started
### 1. Create an android project with empty activity
### 2. Add dependendies to your build gradle 
In build gradle ((Module:app)

    def room_version = "1.1.1"
    implementation "android.arch.persistence.room:runtime:$room_version"
    annotationProcessor "android.arch.persistence.room:compiler:$rootProject.roomVersion"
    androidTestImplementation "android.arch.persistence.room:testing:$rootProject.roomVersion"

    def lifecycle_version = "1.1.1"
    implementation "android.arch.lifecycle:extensions:$lifecycle_version"
    annotationProcessor "android.arch.lifecycle:compiler:$rootProject.archLifecycleVersion"
--------------------------------
In build gradle (Project: myroomdbdemo)

    ext {
    roomVersion = '1.1.1'
    archLifecycleVersion = '1.1.1'
    }
--------------------------------
### 3. Create an entity class with annotation @Entity(tableName="Your Table Name")

<img src="https://github.com/54662579/MyRoomDB/blob/master/image/entity.PNG" />

### 4. Create a Data Access Object (DAO) interface for CRUD operations
<img src="https://github.com/54662579/MyRoomDB/blob/master/image/daoclass.PNG" />

### 5. Create a abstract Room Database class that extends RoomDatabase.

    @Database(entities = {Product.class}, version = 1)
    public abstract class ProductRoomDatabase extends RoomDatabase {
        //specify the DAO that database is going to work with
        public abstract ProductDao productDao();

        /*Make ProductRoomDatabase a singleton to prevent having multiple instances of the database
        opened at the same time.*/
        public static volatile ProductRoomDatabase INSTANCE;


        static ProductRoomDatabase getDatabase (final Context context){
            if (INSTANCE == null) {
                synchronized (ProductRoomDatabase.class){
                    if (INSTANCE == null) {
                        //create a database
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                ProductRoomDatabase.class, "product_database").addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(new Runnable() {
                                    @Override
                                    public void run() {
                                        //prepopulate some sample data
                                        getDatabase(context).productDao().insertAll(Product.initialiseData());
                                    }
                                });
                            }
                        })
                                .allowMainThreadQueries()
                                .build();
                    }
                }
            }
            return INSTANCE;
        }
    }



### 6. Create a Database repository that acts as a manager for multiple data sources if applicable
    public class ProductRepository {

        //add member variables
        private ProductDao mProductDao;

        //create constructor that connects to database and initialise member variables
        public ProductRepository(Application application){
            ProductRoomDatabase db = ProductRoomDatabase.getDatabase(application);
            mProductDao = db.productDao();
        }

        //insert a Product
        public void insert(Product product){mProductDao.insert(product);}
        public void insertAll(Product... products) {mProductDao.insertAll(products);}

        //populate the list of product
        public List<Product> getAll(){return mProductDao.getAll();}

        //get product by id
        public Product get(int id) {return mProductDao.get(id);}

        //update
        public void update(Product product){mProductDao.update(product);}

        //delete
        public void delete(Product product){mProductDao.delete(product);}

        public void deleteAll(){mProductDao.deleteAll();}
    }

### 7. Create a View Model that communicates between the user interfaces (UI) and the data repository. 
    public class ProductViewModel extends AndroidViewModel {

        private ProductRepository productRepository;

        public ProductViewModel(@NonNull Application application) {
            super(application);
            productRepository = new ProductRepository(application);
        }

        public void insert(Product product){ productRepository.insert(product);}
        public void insertAll(Product... products){productRepository.insertAll(products);}
        public Product get(int id){return productRepository.get(id);}
        public List<Product> getAll() {return productRepository.getAll(); }
        public void update(Product product){productRepository.update(product);}
        public void delete(Product product){productRepository.delete(product);}
    }
    
## Saving/Loading database from Activity
   * Saving (See AddProduct.java for the implementation detail)                                    
        <img src="https://github.com/54662579/MyRoomDB/blob/master/image/addnew.PNG" width="200" />
   * List Loading (See ListProduct.java and RecyclerViewAdapter.java for the implementation detail)
        <img src="https://github.com/54662579/MyRoomDB/blob/master/image/listproduct.PNG" width="200" />


