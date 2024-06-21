import {FormLayout} from '@vaadin/react-components/FormLayout.js';
import {TextField} from '@vaadin/react-components/TextField.js';
import {PasswordField} from '@vaadin/react-components/PasswordField.js';
import Button from "@mui/material/Button";
import {UserEndpoint} from "Frontend/generated/endpoints";
import {useForm} from "@vaadin/hilla-react-form";
import {DatePicker} from '@vaadin/react-components/DatePicker.js';
import {CheckboxGroup, Checkbox, EmailField, MultiSelectComboBox} from "@vaadin/react-components";
import {useEffect} from "react";
import CreateUserDto from "Frontend/generated/com/skgworks/restorect/dto/CreateUserDto";
import CreateUserDtoModel from "Frontend/generated/com/skgworks/restorect/dto/CreateUserDtoModel";

export default function CreateUser() {
    const responsiveSteps = [
        {minWidth: '0', columns: 1},
        {minWidth: '500px', columns: 2},
    ];

    const items = [
        {
            label: "User",
            value: "USER",
        },
        {
            label: "Admin",
            value: "ADMIN",
        },
    ];


    const {model, submit, field, addValidator} = useForm(CreateUserDtoModel, {
        onSubmit: async (user) => {
            await UserEndpoint.createUser(user);
        }
    });


    useEffect(() => {
        addValidator({
            message: 'Please check that the password is repeated correctly',
            validate: (value: CreateUserDto) => {
                if (value.password != value.confirmPassword) {
                    return [{property: model.confirmPassword}];
                }
                return [];
            }
        });
    }, []);

    return (
        <FormLayout responsiveSteps={responsiveSteps}>
            <TextField label="First name" {...field(model.name)}/>
            <TextField label="Last name"  {...field(model.surname)}/>
            <TextField label="Username" {...field(model.username)}/>
            <DatePicker label="Birthdate" {...field(model.birthdate)}/>
            <EmailField label="Email" {...field(model.email)}/>
            <PasswordField label="Password" {...field(model.password)}/>
            <PasswordField label="Confirm password" {...field(model.confirmPassword)}/>
            <MultiSelectComboBox label="Roles" items={items} {...field(model.roles)}/>
            {/*<CheckboxGroup label="Roles"
                           {...field(model.roles)}>
                {items.map(option => (
                    <Checkbox key={option.value} value={option.value}
                              label={option.label} />

                ))}
            </CheckboxGroup>*/}
            <Button variant="contained" onClick={submit}>Create</Button>
        </FormLayout>
    );
}
