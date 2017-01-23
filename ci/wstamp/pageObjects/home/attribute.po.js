"use strict";

var AttributePanel = function() {

    var me = $('wstamp-attribute-mini');

    var searchInput = me.element(by.model('attributeService.searchText'));

    this.search = function(text){
        searchInput.sendKeys(text);
    };

    this.getStampElement = function(name){
         return me.element(by.cssContainingText('.stamp .displayName', name));
    }

    this.addAll = function(){
        me.element(by.css('.fa-plus')).click();
    };

    this.getAttributeElement = function(name){
        var ele = me.element(by.cssContainingText('wstamp-attribute .displayName', name));
        var grandparent = ele.element(by.xpath('..')).element(by.xpath('..'));
        return grandparent.$('i.fa.toggle.selectionIcon');
    };

    this.selectStamp = function(name){
        this.getStampElement(name).click();
    }

    this.selectAttribute = function(name){
        this.getAttributeElement(name).click();
    };

    this.addAllForStamp = function(name){
        this.getStampElement(name).click();
        this.addAll();
    }

    this.saveBasket = function(name){
        me.$('.saveBtn').click();
        var modal = $('.modal');
        modal.element(by.model('stamp.name')).sendKeys(name);
        modal.element(by.cssContainingText('button', 'Save')).click();
    }

    this.deleteBasket = function(name){
        var ele = this.getStampElement(name);
        browser.actions().mouseMove( ele ).perform();
        element(by.css('wstamp-popover-content .fa-trash')).click();
        element(by.cssContainingText('.modal button', 'Confirm')).click();
    }    

  };

  module.exports = AttributePanel