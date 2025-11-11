package com.example.GearUp_GarageManagementSystem.services;

import com.example.GearUp_GarageManagementSystem.execptions.CustomException;
import com.example.GearUp_GarageManagementSystem.models.dtos.AddProductRequestDTO;
import com.example.GearUp_GarageManagementSystem.models.dtos.AddProductResponseDTO;
import com.example.GearUp_GarageManagementSystem.models.entities.Factory;
import com.example.GearUp_GarageManagementSystem.models.entities.Product;
import com.example.GearUp_GarageManagementSystem.models.entities.User;
import com.example.GearUp_GarageManagementSystem.repositories.FactoryRepository;
import com.example.GearUp_GarageManagementSystem.repositories.ProductRepository;
import com.example.GearUp_GarageManagementSystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FactoryRepository factoryRepository;


    //Add product :
    public AddProductResponseDTO addProduct(AddProductRequestDTO dto){

        Factory factory=factoryRepository.findById(dto.getFactoryId())
                .orElseThrow(()-> new CustomException("Factory Not Found ", HttpStatus.NOT_FOUND));


        Product product=new Product();
        product.setProductName(dto.getProductName());
        product.setProductDescription(dto.getProductDescription());
        product.setProductPrice(dto.getProductPrice());
        product.setProductImageUrl(dto.getProductImageURL());
        product.setCategory(dto.getCategory());
        product.setIsAvailable(dto.getIsAvailable());
        product.setQuantity(dto.getQuantity());
        product.setFactory(factory);
        //product.setOwner(owner);

        productRepository.save(product);

        return new AddProductResponseDTO(
                product.getId(),
                product.getProductName(),
                product.getCategory(),
                product.getProductPrice(),
                product.getQuantity(),
                factory.getFactoryName(),
                product.getIsAvailable(),
                product.getProductImageUrl()
        );
    }



    //Get all products :
    public List<AddProductResponseDTO> getAllProducts(){
        return productRepository.findAll().stream()
                .map(p-> new AddProductResponseDTO(
                        p.getId(),
                        p.getProductName(),
                        p.getCategory(),
                        p.getProductPrice(),
                        p.getQuantity(),
                        p.getFactory().getFactoryName(),
                        p.getIsAvailable(),
                        p.getProductImageUrl()

                ))
                .toList();
    }



    //Get Product By Id :
    public AddProductResponseDTO getProductById(Long productId){

        Product product=productRepository.findById(productId)
                .orElseThrow(()-> new CustomException("Product Not Found : ",HttpStatus.NOT_FOUND));


       return new AddProductResponseDTO(
               product.getId(),
               product.getProductName(),
               product.getCategory(),
               product.getProductPrice(),
               product.getQuantity(),
               product.getFactory().getFactoryName(),
               product.getIsAvailable(),
               product.getProductImageUrl()
       );
    }



    //Update product :
    public AddProductResponseDTO updateProductDetails(Long id,AddProductRequestDTO dto){
        Product product=productRepository.findById(id)
                .orElseThrow(()-> new CustomException("Product Not Found with Id : "+ id , HttpStatus.NOT_FOUND));


        Factory factory=factoryRepository.findById(dto.getFactoryId())
                .orElseThrow(()-> new CustomException("Factory Not Found with Id : "+ id , HttpStatus.NOT_FOUND));

        product.setProductName(dto.getProductName());
        product.setProductDescription(dto.getProductDescription());
        product.setProductPrice(dto.getProductPrice());
        product.setProductImageUrl(dto.getProductImageURL());
        product.setCategory(dto.getCategory());
        product.setIsAvailable(dto.getIsAvailable());
        product.setQuantity(dto.getQuantity());
        product.setFactory(factory);

        productRepository.save(product);

        return new AddProductResponseDTO(
                product.getId(),
                product.getProductName(),
                product.getCategory(),
                product.getProductPrice(),
                product.getQuantity(),
                factory.getFactoryName(),
                product.getIsAvailable(),
                product.getProductImageUrl()
        );
    }



    //Delete product :
    public void deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException("Product not found", HttpStatus.NOT_FOUND));

        productRepository.delete(product);
    }




    //Filter products by category, availability, factory :
    public List<AddProductResponseDTO> filterProducts(String category, Boolean isAvailable, Long factoryId) {
        List<Product> products = productRepository.findAll(); // start with all

        if (category != null && !category.isEmpty()) {
            products = products.stream().filter(p -> category.equalsIgnoreCase(p.getCategory())).toList();
        }
        if (isAvailable != null) {
            products = products.stream().filter(p -> p.getIsAvailable().equals(isAvailable)).toList();
        }
        if (factoryId != null) {
            products = products.stream().filter(p -> p.getFactory().getFactoryId().equals(factoryId)).toList();
        }

        return products.stream()
                .map(p -> new AddProductResponseDTO(
                        p.getId(),
                        p.getProductName(),
                        p.getCategory(),
                        p.getProductPrice(),
                        p.getQuantity(),
                        p.getFactory().getFactoryName(),
                        p.getIsAvailable(),
                        p.getProductImageUrl()
                ))
                .toList();
    }



    //get Products Paged And Filtered :
    public Page<AddProductResponseDTO> getProductsPagedFiltered(
            String category, Boolean isAvailable, Long factoryId,
            int page, int size, String sortBy
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).ascending());

        Page<Product> productPage = productRepository.findAll(pageable);

        // Apply additional filters if needed manually
        if (category != null || isAvailable != null || factoryId != null) {
            productPage = productPage.map(p -> p); // optional: filter manually if repository method doesn't cover all
        }

        return productPage.map(p -> new AddProductResponseDTO(
                p.getId(),
                p.getProductName(),
                p.getCategory(),
                p.getProductPrice(),
                p.getQuantity(),
                p.getFactory().getFactoryName(),
                p.getIsAvailable(),
                p.getProductImageUrl()
        ));
    }



}
