//
//  MyCar.m
//  CarCola
//
//  Created by Hao Zhou on 3/5/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import "MyCar.h"

@implementation MyCar

- (id)initWithMake: (NSString *)aCarMake
             model: (NSString *)aCarModel
              trim: (NSString *)aCarTrim
              year: (NSString *)aCarYear
             color: (NSString *)aCarColor
                notes:(NSString *)aNotes
//                photo:(NSString *)aPhotoLink
{
    if(self = [super init])
    {
        _carMake = aCarMake;
        _carModel = aCarModel;
        _carTrim = aCarTrim;
        _carYear = aCarYear;
        _carColor = aCarColor;
        _carNotes = aNotes;
//        _carPhotoLink = aPhotoLink;
    }
    return self;
}

-(void)encodeWithCoder:(NSCoder *)encoder
{
    [encoder encodeObject:self.carMake forKey:@"make"];
    [encoder encodeObject:self.carModel forKey:@"model"];
    [encoder encodeObject:self.carTrim forKey:@"trim"];
    [encoder encodeObject:self.carYear forKey:@"year"];
    [encoder encodeObject:self.carColor forKey:@"color"];
    [encoder encodeObject:self.carNotes forKey:@"notes"];
//    [encoder encodeObject:self.carPhotoLink forKey:@"link"];
}

-(id)initWithCoder:(NSCoder *)decoder
{
    self = [super init];
    if(self != nil)
    {
        self.carMake = [decoder decodeObjectForKey:@"make"];
        self.carModel = [decoder decodeObjectForKey:@"model"];
        self.carTrim = [decoder decodeObjectForKey:@"trim"];
        self.carYear = [decoder decodeObjectForKey:@"year"];
        self.carColor = [decoder decodeObjectForKey:@"color"];
        self.carNotes = [decoder decodeObjectForKey:@"notes"];
//        self.carPhotoLink = [decoder decodeObjectForKey:@"link"];
        
    }
    return self;
}


//-(NSString *)carMake {
//    return carMake;
//}
//
//-(NSString *)carModel {
//    return carModel;
//}
//
//-(NSString *)carTrim {
//    return carTrim;
//}
//
//-(NSString *)carYear {
//    return carYear;
//}
//
//-(NSString *)carColor {
//    return carColor;
//}
//
//-(NSString *)carNotes {
//    return carNotes;
//}




@end
