package br.com.duka.siqueira.parkapi.web.dto.mapper;

import br.com.duka.siqueira.parkapi.entity.User;
import br.com.duka.siqueira.parkapi.web.dto.UserCreateDTO;
import br.com.duka.siqueira.parkapi.web.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserMapper {

    public static User toUser(UserCreateDTO createDTO) {
        return new ModelMapper().map(createDTO, User.class);
    }


    public static UserResponseDTO toDTO(User user) {
        String role = user.getRole().name().substring("ROLE_".length());
        PropertyMap<User, UserResponseDTO> props = new PropertyMap<User, UserResponseDTO>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserResponseDTO.class);
    }
}
