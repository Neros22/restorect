import React, {useEffect, useState} from 'react';
import {ItemEndpoint} from "Frontend/generated/endpoints";
import {Grid} from '@vaadin/react-components/Grid.js';
import {GridColumn} from '@vaadin/react-components/GridColumn.js';
import ItemDto from "Frontend/generated/com/skgworks/restorect/dto/ItemDto";
import {ViewConfig} from "@vaadin/hilla-file-router/types.js";

export const config: ViewConfig = {
    loginRequired: true,
    menu: {
        title: "Main page"
    }
};

export default function MainView() {
    const [items, setItems] = useState<ItemDto[]>([]);
    useEffect(() => {
        ItemEndpoint.getItems().then(ads => (setItems(ads)));
    }, []);

    return (
        <div>
            <h1>Items</h1>
            <Grid items={items}>
                <GridColumn path="name"/>
                <GridColumn path="price"/>
                <GridColumn path="description"/>
                <GridColumn path="created"/>
            </Grid>
        </div>
    );
}
